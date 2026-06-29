-- ================================================================
-- DN5.0 Week 1 | PL/SQL Exercise 1: Control Structures
-- Schema: Customers, Loans (from provided DDL)
-- ================================================================

-- Run schema creation first (from provided DDL in document)

-- ================================================================
-- SCENARIO 1: Apply 1% discount on loan interest for customers > 60
-- ================================================================
DECLARE
    CURSOR c_senior_customers IS
        SELECT CustomerID, Name,
               TRUNC(MONTHS_BETWEEN(SYSDATE, DOB) / 12) AS age
        FROM Customers;
    v_updated NUMBER := 0;
BEGIN
    FOR rec IN c_senior_customers LOOP
        IF rec.age > 60 THEN
            UPDATE Loans
            SET InterestRate = GREATEST(InterestRate - 1, 0)
            WHERE CustomerID = rec.CustomerID;

            IF SQL%ROWCOUNT > 0 THEN
                DBMS_OUTPUT.PUT_LINE(
                    'DISCOUNT APPLIED -> Customer: ' || rec.Name ||
                    ' | Age: ' || rec.age ||
                    ' | 1% discount applied to loan interest rate.'
                );
                v_updated := v_updated + SQL%ROWCOUNT;
            END IF;
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Total loans updated: ' || v_updated);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR - Scenario 1: ' || SQLERRM);
END;
/

-- ================================================================
-- SCENARIO 2: Set IsVIP = TRUE for customers with Balance > $10,000
-- ================================================================
-- Add the column if not present:
-- ALTER TABLE Customers ADD (IsVIP VARCHAR2(5) DEFAULT 'FALSE');

DECLARE
    CURSOR c_all_customers IS
        SELECT CustomerID, Name, Balance FROM Customers;
    v_vip_count NUMBER := 0;
BEGIN
    FOR rec IN c_all_customers LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = rec.CustomerID;
            v_vip_count := v_vip_count + 1;
            DBMS_OUTPUT.PUT_LINE(
                'VIP PROMOTED -> ' || rec.Name ||
                ' | Balance: $' || rec.Balance
            );
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Total VIP customers: ' || v_vip_count);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR - Scenario 2: ' || SQLERRM);
END;
/

-- ================================================================
-- SCENARIO 3: Send reminders for loans due in the next 30 days
-- ================================================================
DECLARE
    CURSOR c_due_loans IS
        SELECT l.LoanID, c.Name, l.LoanAmount,
               l.InterestRate, l.EndDate
        FROM   Loans l
        JOIN   Customers c ON l.CustomerID = c.CustomerID
        WHERE  l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
        ORDER  BY l.EndDate;
    v_count NUMBER := 0;
BEGIN
    FOR rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE(
            'REMINDER -> Dear ' || rec.Name ||
            ', your loan of $' || rec.LoanAmount ||
            ' (ID: ' || rec.LoanID || ') is due on ' ||
            TO_CHAR(rec.EndDate, 'DD-MON-YYYY') ||
            '. Please ensure timely payment to avoid penalties.'
        );
        v_count := v_count + 1;
    END LOOP;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans due in the next 30 days.');
    ELSE
        DBMS_OUTPUT.PUT_LINE(v_count || ' reminder(s) sent.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR - Scenario 3: ' || SQLERRM);
END;
/
