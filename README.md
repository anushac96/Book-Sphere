# Full Stack Book Sharing Network: Book-Sphere
## Introduction
Welcome to the Full Stack Book Sharing Network project. This application is developed using Spring Boot for the backend and Angular for the frontend. It is a comprehensive platform that allows users to manage, share, and borrow books, ensuring secure transactions and seamless user experience.

## Features
### 1. User Registration and Authentication
- **Registration**: Users can register by providing necessary details. Accounts are initially disabled until activated via an email verification code.
- **Login**: Secure login using JWT (JSON Web Tokens) for authentication.
- **Account Activation**: Activation via a secure code sent to the user's email.

### 2. Book Management
- **CRUD Operations**: Users can create, read, update, and delete books.
- **Attributes**: Each book has attributes like title, author, genre, ISBN, synopsis, and cover image.
- **Archiving**: Users can archive books they no longer want to share but want to keep a record of.

### 3. Book Sharing
- **Mark as Shareable**: Users can mark books as available for sharing.
- **Viewing Available Books**: Users can view the list of shareable books.
- **Sharing Mechanism**: Functionality to share a book with another user.

### 4. Book Borrowing
- **Borrowing Books**: Users can borrow books from each other.
- **Status Tracking**: Track the status of books (available, borrowed, archived).
- **Borrowing Rules**: Prevent multiple users from borrowing the same book simultaneously.

### 5. Book Returning and Feedback
- **Return Books**: Users can return borrowed books.
- **Approval**: Book owners approve returned books.
- **Feedback and Rating**: Users can give feedback and rate the books they borrowed.

## Technologies Used
### Backend
- **Spring Boot 3**: For creating the REST API and managing backend services.
- **Spring Framework 6**: Core framework for building the application.
- **Spring Security & JWT**: For securing the application and managing user authentication.
- **JSR 303 & Spring Validation**: For validating data.
- **OpenAPI & Swagger UI**: For API documentation.

### Frontend
- **Angular**: For building the user interface.
- **Angular Component-Based Architecture**: For modular and reusable UI components.
- **Lazy Loading**: For optimizing application performance.
- **Authentication Guards**: For securing routes.
- **OpenAPI Generator**: For generating Angular services.
- **Bootstrap**: For styling the application.

### DevOps
- **Docker**: For containerizing the application, ensuring it runs in any environment.

## Getting Started
1. **Clone the Repository**: Clone the project repository from GitHub.
2. **Backend Setup**:
   - Navigate to the backend directory.
   - Build and run the Spring Boot application.
3. **Frontend Setup**:
   - Navigate to the frontend directory.
   - Install dependencies and run the Angular application.
4. **Docker Setup**:
   - Ensure Docker is installed.
   - Use the provided Docker files to set up the application infrastructure.
5. **API Documentation**: Access Swagger UI for API documentation at `http://localhost:8080/swagger-ui.html`.

## Future Enhancements
- **Containerization**: Use Docker to containerize the application for running outside the IDE.
- **CI/CD Pipeline**: Implement a full CI/CD pipeline for automated testing, building, and deployment.
- **OAuth2 Integration**: Integrate Keycloak for OAuth2 authentication, enabling social logins (GitHub, Facebook, Google, etc.).
- **User Feedback and Community Interaction**: Extend functionalities based on user feedback and community suggestions.

## Contributing
We welcome contributions! Please fork the repository and create pull requests for any improvements or new features. Join our Discord server for discussions and updates.

## License
This project is licensed under the MIT License.

## Docker Setup
1. https://desktop.docker.com/mac/main/amd64/93002/Docker.dmg (I used this version because my MacOs version is 10.15.7)
2. did docket setting in docker-compose.yml
3. cmd `docker-compose up`
4. To see running containers `docker ps`
5. To stop a running container `docker stop <CONTAINER_ID>`
6. To stop a running container `docker start <CONTAINER_ID>`

<!-- ## Git Command for my reference
git pull origin main -->