CREATE OR REPLACE function get_price_for_store(specific_date in date)
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
						WHERE BC.Purchase_Quantity > 0)
					GROUP BY Store_ID
					ORDER BY TOTAL DESC;
		*/

			OPEN CUR FOR
			SELECT S.StoreID, (1 + (SELECT COUNT(*) FROM SALES E WHERE E.Total > S.Total)) AS Rank
			FROM SALES S;
			return CUR;
	END;
/
