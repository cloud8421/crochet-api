CREATE TABLE projects (
  uuid uuid PRIMARY KEY,
  name text,
  layouts jsonb,
  created_at timestamp with time zone DEFAULT now(),
  updated_at timestamp with time zone DEFAULT now()
);
