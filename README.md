# Gated Community Complaint Tracker

A full-stack Java web application built using Jakarta Servlets, JSP, JSTL, Hibernate ORM, and MySQL for managing complaints within a gated residential community.

The application provides separate role-based functionality for residents and administrators. Residents can register, log in, raise complaints, view their complaints, edit complaint details, and track complaint status. Administrators can access a dedicated dashboard, view complaints raised across the community, filter complaints by status, update complaint statuses, and also raise their own complaints.

The project follows a layered architecture with clear separation between Servlet controllers, DAO classes, Hibernate entities, JSP views, database operations, and frontend styling.

---

## Tech Stack

* Java 17
* Jakarta Servlets 6.1
* JSP
* JSTL 3
* Hibernate ORM 7
* Jakarta Persistence API (JPA)
* MySQL
* Maven
* HTML
* CSS
* Apache Tomcat 11
* SLF4J
* Logback
* Eclipse / STS
* Git & GitHub

---

## Features

### User Registration

* Register new residents
* Capture:
  * First name
  * Last name
  * Flat number
  * Mobile number
  * Password
  * User role
* Validate mandatory registration fields
* Validate flat number input
* Prevent duplicate resident registration
* Support `RESIDENT` and `ADMIN` roles
* Require a valid admin authentication code when registering as an administrator
* Display meaningful validation messages for invalid registration data

### Authentication and Session Management

* Login using registered mobile number and password
* Session-based authentication using `HttpSession`
* Store logged-in user information in the HTTP session
* Role-based dashboard navigation after successful login
* Separate dashboards for residents and administrators
* Session validation before protected operations
* Logout functionality with session invalidation
* Invalid role handling

### Resident Features

* Register a resident account
* Login using mobile number and password
* Access a dedicated resident dashboard
* Raise new complaints
* Automatically associate a complaint with the logged-in resident
* View complaints belonging to the logged-in resident
* Edit existing complaint details
* Update:
  * Complaint category
  * Description
  * Priority
* Track the current complaint status
* Navigate safely between complaint pages and the dashboard
* Logout from the application

### Admin Features

* Register as an administrator using a valid admin authentication code
* Login using administrator credentials
* Access a dedicated admin dashboard
* View all complaints raised across the community
* View resident information associated with each complaint
* View:
  * Complaint ID
  * Resident name
  * Flat number
  * Category
  * Description
  * Priority
  * Current status
* Filter complaints based on status
* Update complaint status
* Raise personal complaints using the common complaint filing flow
* Logout from the application

### Complaint Status Management

The application supports the following complaint statuses:

* Pending
* In Progress
* Resolved
* Closed

New complaints are initially assigned the `Pending` status.

Administrators can update the status of individual complaints through the complaint management interface.

---

## Application Roles

### Resident

Residents can:

* Register an account
* Login using their credentials
* Access the resident dashboard
* Raise complaints
* View their own complaints
* Edit complaint details
* Track complaint status
* Logout securely

### Admin

Administrators can:

* Register using a valid admin authentication code
* Login using their credentials
* Access the admin dashboard
* View complaints from all residents
* View resident and flat details for every complaint
* Filter complaints by status
* Update complaint statuses
* Raise their own complaints
* Logout securely

---

## Application Architecture

The project follows a layered web application architecture:

```
Browser / Client
        ↓
JSP View
        ↓
Servlet Controller
        ↓
DAO Layer
        ↓
Hibernate ORM
        ↓
MySQL Database
```

### Controller Layer

Servlet controllers handle:

* HTTP GET and POST requests
* Request parameters
* Input validation
* Session management
* Authentication flow
* Role-based navigation
* Calling DAO methods
* Setting request attributes
* Forwarding requests to JSP views
* Redirecting users when required

### DAO Layer

DAO classes handle:

* Resident registration
* Resident lookup
* Authentication-related database queries
* Saving complaints
* Updating complaints
* Fetching complaints by ID
* Fetching complaints belonging to a resident
* Fetching all complaints
* Filtering complaints by status

### View Layer

JSP pages handle:

* Rendering application pages
* Displaying session data
* Displaying request attributes
* JSTL-based iteration over complaint collections
* Resident and admin dashboards
* Complaint tables
* Registration and login forms
* Complaint filing and editing forms
* Validation messages
* Success messages

---

## Servlet Controllers

The application separates controllers based on responsibility.

### Common Controllers

* `LoginRedirectServlet`
* `LoginServlet`
* `SignUpServlet`
* `LogoutServlet`
* `CancelServlet`

### Resident Controllers

* `RaiseComplaintServlet`
* `ComplaintSubmitServlet`
* `ResidentViewComplaintsServlet`
* `EditComplaintServlet`
* `UpdateComplaintServlet`

### Admin Controllers

* `AdminViewComplaintsServlet`
* `AdminViewComplaintsByStatusServlet`
* `UpdateComplaintStatusServlet`

---

## Servlet URL Mappings

| Servlet | URL Mapping | Purpose |
|---------|-------------|---------|
| `LoginRedirectServlet` | `/loginredirect` | Displays or redirects to the login flow |
| `LoginServlet` | `/login` | Authenticates registered users |
| `SignUpServlet` | `/signup` | Registers new residents or administrators |
| `LogoutServlet` | `/logout` | Logs out the current user |
| `CancelServlet` | `/cancel` | Returns the user to the appropriate dashboard based on role |
| `RaiseComplaintServlet` | `/raiseComplaint` | Opens the complaint filing page |
| `ComplaintSubmitServlet` | `/complaintsubmit` | Creates and saves a new complaint |
| `ResidentViewComplaintsServlet` | `/residentViewComplaints` | Displays complaints belonging to the logged-in resident |
| `EditComplaintServlet` | `/edit` | Loads a complaint for editing |
| `UpdateComplaintServlet` | `/updateComplaint` | Updates an existing complaint |
| `AdminViewComplaintsServlet` | `/adminViewComplaints` | Displays all complaints for administrators |
| `AdminViewComplaintsByStatusServlet` | `/adminViewComplaintsByStatus` | Filters complaints by selected status |
| `UpdateComplaintStatusServlet` | `/updateComplaintStatus` | Updates the status of a complaint |

---

## DAO Layer

### ResidentDAO

Handles resident registration and resident-related persistence operations.

Responsibilities include:

* Saving new resident records
* Supporting the user registration process
* Preventing duplicate registration where applicable

### LoginDAO

Handles authentication-related resident lookup.

Responsibilities include:

* Fetching resident details using mobile number
* Retrieving resident information required during login
* Supporting authentication and session creation

### ComplaintDAO

Handles complaint persistence and retrieval.

Responsibilities include:

* Save a new complaint
* Update an existing complaint
* Find a complaint by complaint ID
* Fetch complaints belonging to a specific resident
* Fetch all complaints
* Fetch complaints by status

The DAO uses parameterized HQL queries for status-based filtering.

Example:

```
"FROM Complaint WHERE status = :status"
```

This is safer and cleaner than building HQL queries through string concatenation.

---

## Entity Relationships

The application maintains a bidirectional relationship between residents and complaints.

```
Resident
    |
    | One-to-Many
    ↓
Complaint

Complaint
    |
    | Many-to-One
    ↓
Resident
```

One resident can raise multiple complaints, while each complaint belongs to one resident.

The relationship is implemented using:

* `@OneToMany`
* `@ManyToOne`
* `@JoinColumn`
* `mappedBy`
* `CascadeType.ALL`

---

## Resident Entity

The `Resident` entity contains:

| Field | Description |
|-------|-------------|
| `residentId` | Unique resident identifier |
| `firstName` | Resident's first name |
| `lastName` | Resident's last name |
| `flatNumber` | Resident's flat number |
| `mobileNumber` | Mobile number used for authentication |
| `password` | Resident account password |
| `role` | User role: `RESIDENT` or `ADMIN` |
| `complaints` | Complaints associated with the resident |

The role is stored using:

```
@Enumerated(EnumType.STRING)
```

Supported roles:

```
ADMIN
RESIDENT
```

---

## Complaint Entity

The `Complaint` entity contains:

| Field | Description |
|-------|-------------|
| `complaintId` | Unique complaint identifier |
| `category` | Complaint category |
| `description` | Detailed complaint description |
| `priority` | Complaint priority |
| `status` | Current complaint status |
| `resident` | Resident associated with the complaint |

---

## Complaint Management Flow

### Raise Complaint Flow

```
Resident / Admin Dashboard
        ↓
Click Raise Complaint
        ↓
RaiseComplaintServlet
        ↓
Complaint Filing JSP
        ↓
Enter Category, Description and Priority
        ↓
ComplaintSubmitServlet
        ↓
Validate Request Data
        ↓
Fetch Logged-in Resident
        ↓
Create Complaint
        ↓
Set Initial Status to Pending
        ↓
ComplaintDAO.saveComplaint()
        ↓
Hibernate ORM
        ↓
MySQL Database
        ↓
Complaint Success Page
```

### View Resident Complaints Flow

```text
Resident Dashboard
        ↓
ResidentViewComplaintsServlet
        ↓
Validate Active Session
        ↓
Get Mobile Number from Session
        ↓
Fetch Resident Details
        ↓
Get Resident ID
        ↓
ComplaintDAO.getComplaintsByResidentId()
        ↓
Set Complaints as Request Attribute
        ↓
Resident Complaints JSP
```

### Edit Complaint Flow

```text
Resident Complaints Page
        ↓
Select Complaint to Edit
        ↓
EditComplaintServlet
        ↓
Fetch Complaint by ID
        ↓
Set Complaint as Request Attribute
        ↓
Edit Complaint JSP
        ↓
Modify Category, Description or Priority
        ↓
UpdateComplaintServlet
        ↓
Validate Input
        ↓
Fetch Existing Complaint
        ↓
Update Selected Fields
        ↓
ComplaintDAO.updateComplaint()
        ↓
Database Updated
```

### Admin Complaint Management Flow

```text
Admin Dashboard
        ↓
AdminViewComplaintsServlet
        ↓
Validate Active Session
        ↓
Fetch All Complaints
        ↓
Set Complaints as Request Attribute
        ↓
Admin Complaint Management JSP
        ↓
Select New Complaint Status
        ↓
UpdateComplaintStatusServlet
        ↓
Fetch Complaint by ID
        ↓
Update Status
        ↓
Persist Changes
```

---

## Complaint Status Filtering

The administrator can filter complaints using dedicated dashboard actions:

* View All Complaints
* View Pending Complaints
* View In Progress Complaints
* View Resolved Complaints
* View Closed Complaints

All status-specific actions use the same servlet:

```
Pending ────────┐
In Progress ────┤
Resolved ───────┼──→ AdminViewComplaintsByStatusServlet
Closed ─────────┘                  ↓
                            Read status parameter
                                   ↓
                    ComplaintDAO.getComplaintsByStatus(status)
                                   ↓
                         Display filtered complaints
```

This avoids unnecessary duplicate servlets and DAO methods for each individual status.

---

## Session Management

After successful authentication, the following user information is stored in `HttpSession`:

```text
residentId
userName
phoneNumber
flatNumber
role
```

Protected servlet operations use:

```
request.getSession(false)
```

to check whether an active session exists.

If no valid session is available, the user is redirected to the login page.

---

## Role-Based Navigation

After successful login, the authenticated user's role determines which dashboard is displayed.

```
Login
    ↓
Validate Mobile Number and Password
    ↓
Create HTTP Session
    ↓
Check User Role
    ↓
┌─────────────────────┬─────────────────────┐
│      RESIDENT       │        ADMIN        │
↓                     ↓
Resident Dashboard    Admin Dashboard
```

The application currently supports two roles:

* `RESIDENT`
* `ADMIN`

The common `CancelServlet` also uses the user's session role to return the user to the appropriate dashboard.

---

## Registration Validation

The signup flow validates:

* First name is mandatory
* Last name is mandatory
* Flat number is mandatory
* Flat number must contain valid numeric data
* Mobile number is mandatory
* Password is mandatory
* Role selection is mandatory
* Admin authentication code is mandatory for admin registration
* Admin authentication code must be valid
* Duplicate resident registration is prevented

Validation errors are sent back to the JSP using request attributes and displayed to the user.

---

## Complaint Validation

Complaint creation and update operations validate:

* Complaint category must not be null or blank
* Complaint description must not be null or blank
* Complaint ID must be valid
* Resident must exist before resident-specific complaints are retrieved

---

## Custom Exceptions

The project contains custom runtime exceptions for invalid application states.

### ComplaintNotFoundException

Used when an invalid complaint ID is provided.

### ResidentNotFoundException

Used when a resident cannot be found for the supplied resident ID.

---

## Logging

The application uses:

* SLF4J
* Logback

Logging is used in servlet controllers to track:

* Servlet execution flow
* Login operations
* Session validation
* Resident lookup
* Complaint retrieval
* Request forwarding
* Warning scenarios

The Logback configuration is stored in:

```
src/main/resources/logback.xml
```

---

## Project Structure

```
gated-Community-complaint-tracker
│
├── src/main/java
│   │
│   ├── com/DAO
│   │   ├── ComplaintDAO.java
│   │   ├── LoginDAO.java
│   │   └── ResidentDAO.java
│   │
│   ├── com/controller
│   │   ├── CancelServlet.java
│   │   ├── LoginRedirectServlet.java
│   │   ├── LoginServlet.java
│   │   ├── LogoutServlet.java
│   │   ├── SignUpServlet.java
│   │   │
│   │   ├── admin
│   │   │   ├── AdminViewComplaintsByStatusServlet.java
│   │   │   ├── AdminViewComplaintsServlet.java
│   │   │   └── UpdateComplaintStatusServlet.java
│   │   │
│   │   └── resident
│   │       ├── ComplaintSubmitServlet.java
│   │       ├── EditComplaintServlet.java
│   │       ├── RaiseComplaintServlet.java
│   │       ├── ResidentViewComplaintsServlet.java
│   │       └── UpdateComplaintServlet.java
│   │
│   ├── com/entity
│   │   ├── Complaint.java
│   │   └── Resident.java
│   │
│   ├── com/enums
│   │   └── RoleEnum.java
│   │
│   ├── com/exception
│   │   ├── ComplaintNotFoundException.java
│   │   └── ResidentNotFoundException.java
│   │
│   └── com/util
│       └── HibernateUtil.java
│
├── src/main/resources
│   ├── hibernate.cfg.xml
│   └── logback.xml
│
├── src/main/webapp
│   │
│   ├── resources
│   │   └── css
│   │       └── app.css
│   │
│   ├── WEB-INF
│   │   ├── views
│   │   │   ├── admindashboard.jsp
│   │   │   ├── adminviewcomplaints.jsp
│   │   │   ├── complaintfiling.jsp
│   │   │   ├── complaintsuccess.jsp
│   │   │   ├── complaintupdatesuccess.jsp
│   │   │   ├── editComplaint.jsp
│   │   │   ├── login.jsp
│   │   │   ├── residentdashboard.jsp
│   │   │   └── residentviewcomplaints.jsp
│   │   │
│   │   └── web.xml
│   │
│   └── index.jsp
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## Database Implementation

The application uses Hibernate ORM for persistence and database operations.

### Hibernate Features Used

* Entity mapping
* Hibernate `SessionFactory`
* Hibernate `Session`
* Transactions
* HQL queries
* Parameterized queries
* `persist()`
* `merge()`
* `find()`
* Entity relationships
* Enum persistence
* MySQL integration

---

## Database Schema

### Residents Table

| Column | Description |
|--------|-------------|
| `resident_id` | Unique resident identifier |
| `first_name` | Resident's first name |
| `last_name` | Resident's last name |
| `flat_number` | Resident's flat number |
| `mobile_number` | Mobile number used for authentication |
| `password` | Resident account password |
| `role` | User role such as `RESIDENT` or `ADMIN` |

### Complaints Table

| Column | Description |
|--------|-------------|
| `complaint_id` | Unique complaint identifier |
| `category` | Complaint category |
| `complaint_description` | Detailed complaint description |
| `priority` | Complaint priority |
| `status` | Current complaint status |
| `resident_id` | Foreign key referencing the resident |

---

## UI Features

The application includes a shared modern CSS design system through:

```text
src/main/webapp/resources/css/app.css
```

The UI includes:

* Dedicated resident dashboard
* Dedicated admin dashboard
* Modern login interface
* Complaint filing form
* Complaint editing form
* Resident complaint table
* Admin complaint management table
* Status update dropdown
* Complaint status filtering
* Validation messages
* Success pages
* Role-aware navigation
* Consistent buttons, cards, forms, tables, and layout styling

---

## How to Run

### Clone Repository

```bash
git clone https://github.com/Harsha-vardhan-7/gated-Community-complaint-tracker.git
```

### Configure Database

Update the MySQL database configuration inside:

```text
src/main/resources/hibernate.cfg.xml
```

Configure:

* JDBC URL
* Database username
* Database password
* MySQL dialect and other required Hibernate properties

Ensure that:

* MySQL Server is running
* The required database exists
* Database credentials are correct

### Build Project

```bash
mvn clean install
```

### Deploy Application

Deploy the generated WAR file to Apache Tomcat 11, or run the application directly through Eclipse/STS using the configured Tomcat 11 server runtime.

### Access Application

Open the deployed application URL in a browser and register or log in using resident or administrator credentials.

---

## Learning Outcomes

This project strengthened understanding of:

* Java web application development
* Jakarta Servlets
* JSP
* JSTL
* Servlet lifecycle and URL mappings
* HTTP GET and POST requests
* Request parameters
* Request attributes
* HTTP session management
* Role-based navigation
* User registration and authentication flow
* Hibernate ORM
* Hibernate Session and SessionFactory
* HQL queries
* Parameterized queries
* Entity relationships
* DAO design pattern
* MVC-style architecture
* Server-side validation
* Custom exception handling
* JSP Expression Language
* Servlet-to-JSP communication
* SLF4J logging
* Logback configuration
* Maven dependency management
* Apache Tomcat deployment
* MySQL database integration
* Git and GitHub workflow

---

## Future Improvements

Planned future enhancements:

* Password hashing using BCrypt
* Servlet filters for centralized authentication and authorization
* Stronger role-based access control
* Replace String-based complaint statuses with an enum
* Complaint assignment to maintenance staff
* Complaint comments and progress updates
* Email or notification support
* Complaint history and audit trail
* Pagination for complaint tables
* Search and advanced filtering
* Dashboard statistics and charts
* File and image attachments for complaints
* Centralized exception handling
* Improved DAO resource management using try-with-resources
* Unit testing
* Integration testing
* Docker deployment
* Database migration management

---

## Repository

GitHub repository:

https://github.com/Harsha-vardhan-7/gated-Community-complaint-tracker.git
