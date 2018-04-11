CREATE OR REPLACE function get_price_for_store(specific_id in int, specific_date in date)
	return float
	AS total_earned float;
	BEGIN
		SELECT SUM(TOTAL) into total_earned
		FROM
			(SELECT (BC.Purchase_Quantity * C.Price) as TOTAL, C.COFFEE_ID
			FROM PURCHASE P
			JOIN BUYCOFFEE BC ON P.Purchase_ID = BC.Purchase_ID
			JOIN COFFEE C ON BC.COFFEE_ID = C.COFFEE_ID
			WHERE BC.Purchase_Quantity > 0 and P.Store_ID = specific_id and P.purchase_time > specific_date);
		IF total_earned is NULL THEN
			return 0;
		ELSE
			return total_earned;
		END IF;
	END;
/
