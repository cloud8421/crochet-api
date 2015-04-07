-- name: all
-- Finds all projects
SELECT *
FROM projects
ORDER BY created_at ASC
LIMIT :limit;

-- name: by-user
-- Finds all projects for a given user
SELECT *
FROM projects
WHERE facebook_id = :facebook_id
LIMIT :limit;

-- name: project-count
-- Counts all projects
SELECT count(*)
FROM projects;

-- name: find-by-uuid
-- Finds a project by uuid
SELECT *
FROM projects
WHERE uuid = :uuid::uuid;

-- name: create!
-- Create a new project
INSERT INTO projects(uuid, name, layouts, facebook_id)
VALUES (
  uuid_generate_v4(),
  :name,
  :layouts::jsonb,
  :facebook_id
);

-- name: update!
-- Updates a project by uuid
UPDATE projects
SET name = :name,
    layouts = :layouts::jsonb,
    updated_at = now()
WHERE uuid = :uuid::uuid;

-- name: delete!
-- Delete a project by uuid
DELETE FROM projects
WHERE uuid = :uuid::uuid;
