# Code Review Fixes - Task Tracker

- [x] 0. Read and analyze all project files
- [x] 1. Fix register endpoint path mismatch (AuthController, SecurityConfig, JwtFilter, Readme)
- [x] 2. Fix Access Denied handler message + status (GlobalExceptionHandler)
- [x] 3. Add @Valid to login + wrap login response in ApiResponse (AuthController)
- [x] 4. Externalize DB/JWT secrets via environment variables (application.properties, Readme)
- [x] 5. Add @Min(1) validation on ExpenseRequestDTO.amount
- [x] 6. Add BadCredentials / DataIntegrityViolation / IllegalArgumentException / generic exception handlers
- [x] 7. Remove duplicate registration logic (UserService, UserServiceImpl)
- [x] 8. Use real roles from user entity instead of hardcoded ROLE_USER (CustomUserDetailsService, JwtFilter)
- [x] 9. Add @Transactional + sortBy whitelist (ExpenseServiceImpl)
- [x] 10. (Skipped per user request) Add unit/integration tests
- [x] 11. Fix ExpenseController delete mapping missing leading slash
- [x] 12. Update Readme.md endpoints to match new routes

