-- We are no longer using functions. this file is for reference only.

CREATE OR REPLACE PROCEDURE CUSTOMER_SPENT(K IN INT, CUR OUT SYS_REFCURSOR)
IS
BEGIN

	OPEN CUR FOR SELECT CUSTOMER_ID
		FROM
			(SELECT S.CUSTOMER_ID, 1+ (SELECT COUNT(*) FROM CUST_RANK T WHERE T.TOTAL>S.TOTAL) AS RANK
			FROM CUST_RANK S)
		WHERE RANK <= K;
END;
/

CREATE OR REPLACE function get_price_for_store(K in int)
	return SYS_REFCURSOR
	AS
	CUR SYS_REFCURSOR;
	BEGIN
		/*
			CREATE OR REPLACE VIEW SALES (Total, StoreID) AS
					SELECT SUM(TOTAL) as TOTAL, Store_ID as StoreID
					FROM
						(SELECT (BC.Purchase_Quantity * C.Price) as TOTAL, P.Store_ID as Store_ID
						FROM PURCHASE P
						JOIN BUYCOFFEE BC ON P.Purchase_ID = BC.Purchase_ID
						JOIN COFFEE C ON BC.COFFEE_ID = C.COFFEE_ID
						WHERE BC.Purchase_Quantity > 0 AND P.Purchase_Time > to_date('',''))
					GROUP BY Store_ID
					ORDER BY TOTAL DESC;
		*/

			OPEN CUR FOR
			SELECT S.StoreID
			FROM (
					SELECT S.StoreID, (1 + (SELECT COUNT(*) FROM SALES E WHERE E.Total > S.Total)) AS Rank
					FROM SALES S)
			WHERE RANK >= K;
			return CUR;
	END;
/
