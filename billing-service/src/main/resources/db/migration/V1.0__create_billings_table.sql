CREATE TABLE IF NOT EXISTS public.billings
(
    user_id     VARCHAR(255) NOT NULL,
    description TEXT,
    type        VARCHAR(50),
    date        TIMESTAMPTZ NOT NULL,
    quantity    INT,
    cost        DECIMAL(19, 2),
    CONSTRAINT pk_billings_user_date_type PRIMARY KEY (user_id, date, type)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE public.billings TO "optimalpacking-billing-user";