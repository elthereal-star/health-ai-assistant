INSERT IGNORE INTO food_dict (code, name, calories_per_100g, protein_per_100g, carbs_per_100g, fat_per_100g, default_unit, default_portion) VALUES
('beef_noodles', 'beef noodles', 550, 25, 65, 18, 'bowl', 500),
('rice', 'rice', 130, 2.7, 28, 0.3, 'bowl', 150),
('egg', 'egg', 155, 13, 1.1, 11, 'piece', 50),
('apple', 'apple', 52, 0.3, 14, 0.2, 'piece', 180),
('chicken_breast', 'chicken breast', 165, 31, 0, 3.6, 'g', 100);

INSERT IGNORE INTO exercise_dict (code, name, calories_per_minute) VALUES
('running', 'running', 10),
('walking', 'walking', 4),
('cycling', 'cycling', 7),
('swimming', 'swimming', 9),
('yoga', 'yoga', 3);