-- Insert 5 sample locations into the location table
INSERT INTO location (name, address, latitude, longitude)
VALUES
    ('Location 1', 'Address 1', 40.7128, -74.0060),
    ('Location 2', 'Address 2', 34.0522, -118.2437),
    ('Location 3', 'Address 3', 51.5074, -0.1278),
    ('Location 4', 'Address 4', 48.8566, 2.3522),
    ('Location 5', 'Address 5', 52.5200, 13.4050);

-- Insert a few sample events into the event table
INSERT INTO event (name, description, date, start_time, end_time, location_id)
VALUES
    ('Event 1', 'Description 1', '2023-09-01', '10:00:00', '12:00:00', 1),
    ('Event 2', 'Description 2', '2023-09-02', '14:00:00', '16:00:00', 2),
    ('Event 3', 'Description 3', '2023-09-03', '11:30:00', '13:30:00', 3),
    ('Event 4', 'Description 4', '2023-09-04', '09:00:00', '11:00:00', 4),
    ('Event 5', 'Description 5', '2023-09-05', '15:00:00', '17:00:00', 5);
