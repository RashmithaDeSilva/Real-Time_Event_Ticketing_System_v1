-- Enable foreign key constraints (needed for foreign keys to work in SQLite)
PRAGMA foreign_keys = ON;


CREATE TABLE system_config (
    id INTEGER PRIMARY KEY,
    config_key TEXT NOT NULL UNIQUE,
    config_value INT NOT NULL  -- Use INT if values are strictly integers, or TEXT for flexibility
);

INSERT OR IGNORE INTO system_config (config_key, config_value) 
VALUES 
    ("total_tickets", 50),
    ("ticket_release_rate", 60),
    ("customer_retrieval_rate", 60),
    ("max_ticket_capacity", 500);


-- Table for vendors
CREATE TABLE vendors (
    vendor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    vendor_name TEXT NOT NULL,
    tickets_per_release INTEGER NOT NULL, -- Number of tickets added per release
    release_rate_sec INTEGER NOT NULL  -- Frequency of ticket release in seconds
);


INSERT OR IGNORE INTO vendors (vendor_name, tickets_per_release, release_rate_sec)
VALUES 
    ("vendor 1", 5, 60),
    ("vendor 2", 2, 120),
    ("vendor 3", 1, 30);


-- Table for customers
CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    is_vip BOOLEAN DEFAULT 0  -- 1 if VIP, 0 otherwise
);


-- Table for ticket pool
CREATE TABLE ticket_pool (
    ticket_id INTEGER PRIMARY KEY AUTOINCREMENT,
    vendor_id INTEGER,
    status TEXT DEFAULT 'available', -- Status can be 'available' or 'sold'
    FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id) ON DELETE CASCADE
);


-- Table for sales log
CREATE TABLE sales_log (
    sale_id INTEGER PRIMARY KEY AUTOINCREMENT,
    customer_id INTEGER,
    customer_type TEXT NOT NULL,  -- 'VIP' or 'Regular'
    vendor_id INTEGER,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE SET NULL,
    FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id) ON DELETE SET NULL
);

