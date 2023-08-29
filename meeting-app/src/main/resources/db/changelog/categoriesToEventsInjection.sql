-- Insert 5 categories
INSERT INTO category (name, description) VALUES
                                             ('Category 1', 'Description 1'),
                                             ('Category 2', 'Description 2'),
                                             ('Category 3', 'Description 3'),
                                             ('Category 4', 'Description 4'),
                                             ('Category 5', 'Description 5');

-- Associate existing events with categories
-- Event 1 is associated with Categories 1 and 2
INSERT INTO event_category (event_id, category_id) VALUES (1, 1), (1, 2);

-- Event 2 is associated with Categories 2, 3, and 4
INSERT INTO event_category (event_id, category_id) VALUES (2, 2), (2, 3), (2, 4);

-- Event 3 is associated with Categories 4 and 5
INSERT INTO event_category (event_id, category_id) VALUES (3, 4), (3, 5);

-- Event 4 is associated with Category 1
INSERT INTO event_category (event_id, category_id) VALUES (4, 1);

-- Event 5 is not associated with any category
