ALTER TABLE projects
ADD COLUMN facebook_id text;

CREATE INDEX idx_projects_facebook_id ON projects(facebook_id);
