CREATE TABLE system_config (
    id INTEGER PRIMARY KEY,  -- Use INTEGER PRIMARY KEY instead of AUTO_INCREMENT
    config_key VARCHAR(255) NOT NULL UNIQUE,
    config_value INT NOT NULL
);

INSERT INTO system_config (config_key, config_value)
VALUES ("max_ticket_capacity", 100),
       ("ticket_release_rate", 60);


-- Table for tickets
CREATE TABLE tickets (
    id INTEGER PRIMARY KEY,  -- Auto-increment behavior
    ticket_name VARCHAR(255) NOT NULL,
    ticket_type INTEGER NOT NULL,  -- Boolean represented as INTEGER (0 for FALSE, 1 for TRUE)
    availability INTEGER NOT NULL,
    price REAL NOT NULL,  -- SQLite uses REAL for floating-point numbers
    description TEXT
);

-- Table for vendors
CREATE TABLE vendors (
    id INTEGER PRIMARY KEY,  -- Auto-increment behavior
    vendor_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Table for vendor-tickets association
CREATE TABLE vendor_tickets (
    id INTEGER PRIMARY KEY,  -- Auto-increment behavior
    vendor_id INTEGER NOT NULL,
    ticket_id INTEGER NOT NULL,
    FOREIGN KEY (vendor_id) REFERENCES vendors(id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(id),
    UNIQUE (vendor_id, ticket_id)  -- Ensures a ticket can only be associated with a vendor once
);

-- Enable foreign key constraints (needed for foreign keys to work in SQLite)
PRAGMA foreign_keys = ON;


