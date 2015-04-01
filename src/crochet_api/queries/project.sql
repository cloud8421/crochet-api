-- name: all
-- Finds all projects
SELECT *
FROM projects
ORDER BY created_at ASC
LIMIT :limit;

-- name: find-by-uuid
-- Finds a project by uuid
SELECT *
FROM projects
WHERE uuid = :uuid::uuid;

-- name: create!
-- Create a new project
INSERT INTO projects
VALUES (
  uuid_generate_v4(),
  :name,
  :layouts::jsonb
);

-- name: update!
-- Updates a project by name
UPDATE projects
SET name = :name,
    layouts = :layouts::jsonb,
    updated_at = now()
WHERE uuid = :uuid::uuid;
