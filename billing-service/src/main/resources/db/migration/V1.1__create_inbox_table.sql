CREATE TABLE IF NOT EXISTS public.inbox (
                                            id uuid PRIMARY KEY,
                                            created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);