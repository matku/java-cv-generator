# Functions #

## Main page ##

  * Home
  * About
  * Contact
  * Registration
  * Login

### Registration ###

When registering, system is searching in file users.xml if user already exists. Username and email are unique. Application won't allow registration. No verification email will be send to user. Password is hashed via md5 for security reasons.

### Login ###

Login form with session expiration 30 minutes. Session sets username.

## Administration interface ##

  * Create, edit, remove CV (has no number limit)
  * Export CV to language (finally to PDF using LateX)
  * change username
  * change password