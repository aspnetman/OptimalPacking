CREATE TABLE IF NOT EXISTS public.parcels
(
    name   VARCHAR PRIMARY KEY,
    form   VARCHAR NOT NULL,
    symbol CHAR    NOT NULL,
    width  INTEGER NOT NULL,
    height INTEGER NOT NULL
);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE public.parcels TO "optimalpacking-user";