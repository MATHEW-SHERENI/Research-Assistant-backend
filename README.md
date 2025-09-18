
## Backend README.md

```markdown
# Undergraduation.com Admin Dashboard - Backend

Node.js/Express backend API for the Undergraduation.com admin dashboard.

## Features

- RESTful API for student management
- Firebase Firestore integration
- Authentication middleware
- CORS enabled for frontend communication
- CRUD operations for students, notes, and communications

## Tech Stack

- Node.js
- Express.js
- Firebase Admin SDK
- CORS middleware
- Nodemon (development)

## Prerequisites

- Node.js (v14 or higher)
- npm or yarn
- Firebase project with Service Account credentials

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/undergraduation-admin-backend.git
   cd undergraduate-admin-backend

Firebase Service Account Setup

Go to Firebase Console

Select your project → Project Settings → Service Accounts

Generate a new private key and download the JSON file

Rename the file to serviceAccountKey.json and place it in the root directory

Environment Configuration

Copy the .env.example file to .env

Update the environment variables:

env
PORT=5000
FIREBASE_DATABASE_URL=https://your-project-id.firebaseio.com
Running the Application
Start the development server

bash
npm run dev
The server will run on http://localhost:5000

API endpoints will be available at:

GET /api/students - Get all students

GET /api/students/:id - Get a specific student

POST /api/students - Create a new student

PUT /api/students/:id - Update a student

POST /api/students/:id/notes - Add a note to a student

POST /api/students/:id/communications - Add a communication to a student

Seeding Sample Data
Run the seed script to populate the database with sample data:

bash
node scripts/seedData.js
This will create sample student records with interactions, communications, and notes

API Documentation
Students Endpoints
GET /api/students - Retrieve all students

GET /api/students/:id - Retrieve a specific student by ID

POST /api/students - Create a new student

PUT /api/students/:id - Update a student

POST /api/students/:id/notes - Add a note to a student

POST /api/students/:id/communications - Add a communication to a student

POST /api/students/:id/interactions - Add an interaction to a student

Authentication Endpoints
POST /api/auth/verify - Verify authentication token

GET /api/auth/user - Get user data

Project Structure
text
backend/
├── config/
│   └── firebase.js          # Firebase configuration
├── controllers/             # Business logic (optional)
├── models/                  # Data models
│   └── Student.js           # Student model
├── routes/                  # API routes
│   ├── students.js          # Student routes
│   ├── auth.js              # Authentication routes
│   └── communications.js    # Communication routes
├── middleware/              # Custom middleware (optional)
├── scripts/                 # Utility scripts
│   └── seedData.js          # Database seeding script
├── utils/                   # Utility functions
│   └── dateUtils.js         # Date utilities (optional)
├── server.js                # Main server file
└── package.json             # Dependencies and scripts
