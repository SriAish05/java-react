-- ================================================================
-- DN5.0 Week 1 | PL/SQL Exercise 3: Stored Procedures
-- ================================================================

-- ================================================================
-- SCENARIO 1: ProcessMonthlyInterest
-- Applies 1% interest to all Savings accounts
-- ================================================================
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    CURSOR c_savings IS
        SELECT AccountID, Balance, CustomerID
        FROM   Accounts
        WHERE  AccountType = 'Savings'
        FOR UPDATE OF Balance;

    v_new_balance  NUMBER;
    v_count        NUMBER := 0;
BEGIN
    FOR rec IN c_savings LOOP
        v_new_balance := rec.Balance * 1.01;

        UPDATE Accounts
        SET    Balance      = v_new_balance,
               LastModified = SYSDATE
        WHERE  AccountID    = rec.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Account ' || rec.AccountID ||
            ': $' || rec.Balance || ' -> $' || ROUND(v_new_balance, 2)
        );
        v_count := v_count + 1;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to ' || v_count || ' account(s).');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR in ProcessMonthlyInterest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Execute:
EXEC ProcessMonthlyInterest;

-- ================================================================
-- SCENARIO 2: UpdateEmployeeBonus
-- Adds a bonus % to employees in a given department
-- ================================================================
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department   IN VARCHAR2,
    p_bonus_percent IN NUMBER
) IS
    v_updated NUMBER;
BEGIN
    IF p_bonus_percent <= 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Bonus percent must be greater than 0.');
    END IF;

    UPDATE Employees
    SET    Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE  UPPER(Department) = UPPER(p_department);

    v_updated := SQL%ROWCOUNT;

    IF v_updated = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE(
            'Bonus of ' || p_bonus_percent || '% applied to ' ||
            v_updated || ' employee(s) in ' || p_department || ' department.'
        );
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR in UpdateEmployeeBonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Execute:
EXEC UpdateEmployeeBonus('IT', 10);
EXEC UpdateEmployeeBonus('HR', 15);

-- ================================================================
-- SCENARIO 3: TransferFunds
-- Transfers specified amount between accounts with balance check
-- ================================================================
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) IS
    v_from_balance NUMBER;
    v_to_exists    NUMBER;
BEGIN
    -- Validate amount
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20020, 'Transfer amount must be positive.');
    END IF;

    -- Check source account balance (with row lock to prevent race conditions)
    SELECT Balance INTO v_from_balance
    FROM   Accounts
    WHERE  AccountID = p_from_account
    FOR UPDATE;

    -- Check destination account exists
    SELECT COUNT(*) INTO v_to_exists
    FROM   Accounts
    WHERE  AccountID = p_to_account;

    IF v_to_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20021, 'Destination account ' || p_to_account || ' not found.');
    END IF;

    -- Check sufficient funds
    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(
            -20022,
            'Insufficient funds. Available: $' || v_from_balance ||
            ', Requested: $' || p_amount
        );
    END IF;

    -- Debit source account
    UPDATE Accounts
    SET    Balance      = Balance - p_amount,
           LastModified = SYSDATE
    WHERE  AccountID    = p_from_account;

    -- Credit destination account
    UPDATE Accounts
    SET    Balance      = Balance + p_amount,
           LastModified = SYSDATE
    WHERE  AccountID    = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'SUCCESS: $' || p_amount ||
        ' transferred from Account ' || p_from_account ||
        ' to Account ' || p_to_account || '.'
    );
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Source account ' || p_from_account || ' not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR in TransferFunds: ' || SQLERRM);
END TransferFunds;
/

-- Execute:
EXEC TransferFunds(1, 2, 200);
-- EXEC TransferFunds(1, 2, 999999);  -- Tests insufficient funds error
