-- INSERT INTO topics (topic_name) VALUES ('zowen');

-- SELECT * FROM topics WHERE info IS NULL;

select * from topics where parent_id IS NULL AND topic_name = 'zowen';