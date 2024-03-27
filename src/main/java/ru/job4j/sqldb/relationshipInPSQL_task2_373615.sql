-- many-to-many
CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    Username VARCHAR(50) NOT NULL
);


CREATE TABLE Companies (
    CompanyID SERIAL PRIMARY KEY,
    UserID INT REFERENCES Users(UserID),
    CompanyName VARCHAR(100) NOT NULL
);


CREATE TABLE UserCompany (
    UserID INT REFERENCES Users(UserID),
    CompanyID INT REFERENCES Companies(CompanyID),
    PRIMARY KEY (UserID, CompanyID)
);


-- many-to-one
CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    Username VARCHAR(50) NOT NULL
);


CREATE TABLE Companies (
    CompanyID SERIAL PRIMARY KEY,
    UserID INT REFERENCES Users(UserID),
    CompanyName VARCHAR(100) NOT NULL
);

-- one-to-one
CREATE TABLE UserDetails (
    UserDetailsID SERIAL PRIMARY KEY,
    UserID INT UNIQUE,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL
);

CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    UserDetailsID INT UNIQUE,
    FOREIGN KEY (UserDetailsID) REFERENCES UserDetails(UserDetailsID)
);

