-- name: all
-- Finds all projects
SELECT *
FROM projects
ORDER BY created_at ASC
LIMIT :limit;
