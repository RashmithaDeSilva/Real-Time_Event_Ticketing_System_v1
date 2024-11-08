# Real-Time Event Ticketing System

## 1. Overview of the System
The Real-Time Event Ticketing System manages ticket sales in real time, simulating interactions between vendors **(producers)** and customers **(consumers)**. It includes features like ticket availability management, real-time updates, and support for multiple vendors and ticket types.

## 2. Component Roles

### Command-Line Interface (CLI)
**Purpose**: Primarily used by system administrators for configuration and monitoring.

- **Functions**:
  - **System Configuration**: Set up parameters like total ticket count and ticket release frequency.
  - **Manage Vendors**: Add or remove vendors.
  - **Manage Ticket Types**: Define ticket types (e.g., VIP, General Admission) with specific prices.
  - **Monitor Real-Time Status**: View current ticket inventory by vendor and type.
  - **Sales Log**: Review past sales, tracking ticket purchases.

### Frontend (User Interface)
**Purpose**: Provides a graphical interface for customers and admins. Customers can buy tickets, while admins monitor the system.

- **Functions**:
  - **Display Ticket Availability**: Real-time updates on available tickets.
  - **Admin Control Panel**: Start/stop ticket release and adjust system settings.
  - **Real-Time Notifications**: Inform customers about ticket status and sales.
  - **Purchase Tickets**: Customers can buy tickets directly through the interface.

### API (Backend Logic and Database Interaction)
**Purpose**: Handles core operations, connecting the frontend and CLI to the database and managing real-time ticket handling.

- **Functions**:
  - **Producer-Consumer Pattern**: Manages ticket addition (producers) and sales (consumers) with real-time updates.
  - **Thread Safety**: Supports multiple vendors and customers, ensuring safe multi-threaded operations.
  - **Database Operations**: Add, update, and retrieve data for tickets, sales, vendors, and customers.

### Database
**Purpose**: Stores essential information on vendors, tickets, and sales, supporting both real-time operations and historical data.

- **Tables**:
  - **Vendors Table**: Holds vendor details like name and release rate.
  - **Tickets Table**: Tracks ticket information, availability, and vendor association.
  - **Sales Log Table**: Logs each sale, tracking ticket ID, customer info, and timestamp.

## 3. Component Interactions

1. **System Setup (CLI and API)**:
   - Admins use the CLI to configure vendors, ticket types, and system settings. This data is saved in the Database via the API.
  
2. **Ticket Handling (API and Database)**:
   - The API manages ticket release (by vendors) and purchase (by customers) using multi-threading and synchronization for error-free operations.

3. **Real-Time Updates (Frontend, API, and Database)**:
   - The Frontend shows real-time ticket availability by querying the API, which updates the Database.

4. **Sales Logging (API and Database)**:
   - Every sale is logged by the API in the Sales Log Table, viewable through the CLI or Frontend.

## 4. Example Workflow

1. **Admin Setup (CLI)**:
   - Admins configure vendors and ticket types via CLI (e.g., Vendor A with VIP tickets, Vendor B with General Admission).
   - Configuration details are saved to the Database.

2. **Customer Purchase (Frontend)**:
   - A customer selects a VIP ticket and submits a purchase request.
   - The API processes this, updating ticket availability and logging the sale.

3. **Real-Time Status Check (CLI/Frontend)**:
   - Admins can view real-time ticket status in the CLI, and customers see updated availability on the frontend.

## 5. Key Relationships

- **Vendors and Tickets**: Each vendor is associated with specific ticket types.
- **Tickets and Sales Log**: Each sale entry links to a specific ticket.
- **API and Database**: The API manages ticket inventory, sales, and interactions, keeping all components synchronized.













































