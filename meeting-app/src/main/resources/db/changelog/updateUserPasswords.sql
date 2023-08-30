-- Update password for 'user1'
UPDATE USERS
SET PASSWORD = '$2a$12$8rnsB5M9M.A5Eiao8EPn1OwXw20H8RWfVxhxwa6XBUJ/XRB0dYRoq'
WHERE USERNAME = 'user1';

-- Update password for 'user2'
UPDATE USERS
SET PASSWORD = '$2a$12$hXCoCRcvPiVgLk0Tpuo1f.TZirO9CuXqKSC7h8vnVrG0jMgT.CxAS'
WHERE USERNAME = 'user2';
