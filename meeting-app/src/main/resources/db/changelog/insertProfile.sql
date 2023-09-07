-- Insert dummy data into the 'profile' table
INSERT INTO profile (user_id, sex, job, school, description, photo, city)
VALUES
    (1, 'Male', 'Software Developer', 'University A', 'A software engineer with a passion for coding.', 'photo1.jpg', 'New York'),
    (2, 'Female', 'Data Analyst', 'University B', 'Analyzing data is my expertise.', 'photo2.jpg', 'Los Angeles');

-- Insert dummy data into the 'profile_category' table
INSERT INTO profile_category (profile_id, category_id)
VALUES
    (1, 1),
    (2, 2);
