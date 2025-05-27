RuralBank

#Class Design
1. BankAccount (Abstract Class)
- Base class for all bank accounts.
- Contains common attributes: `accountHolderName`, `accountNumber`, `balance`.
- Defines shared methods like `deposit()` and abstract `withdraw()` and `displayAccountDetails()`.

2. SavingsAccount (Extends BankAccount)
- Adds interest functionality using a fixed interest rate.
- Overrides `withdraw()` to prevent overdrafts.
- Implements interest calculation via `addInterest()` method.

3. CurrentAccount (Extends BankAccount)
- Supports overdraft up to a fixed limit.
- Overrides `withdraw()` to allow balance to go negative within the limit.

4. Customer
- Represents a bank customer.
- Aggregates one or more `BankAccount` objects using a list.
- Contains customer name and a collection of associated accounts.

5. InsufficientBalanceException (Custom Exception)
- Thrown when a withdrawal exceeds allowed limits (e.g., overdraft or insufficient funds).
- Enhances error reporting with user-friendly messages.

6. Main
- Entry point with console-based user interaction.
- Handles user input, displays menu, and manages transactions.

## OOP Concepts Implemented

1. Abstraction
- `BankAccount` is an abstract class that defines a general template for account types without implementing all methods.

2. Inheritance
- `SavingsAccount` and `CurrentAccount` inherit from `BankAccount`, extending its functionality.

3. Encapsulation
- Class attributes are marked `private` or `protected`, accessed and modified via public getters/setters.

4. Polymorphism
- Methods like `deposit()`, `withdraw()`, and `displayAccountDetails()` are invoked on references of type `BankAccount`, enabling runtime polymorphism.

5. Aggregation
- A `Customer` object contains a list of `BankAccount` objects, demonstrating a "has-a" relationship.

6.Exception Handling
- A custom exception (`InsufficientBalanceException`) is used to handle business rule violations such as overdrafts and insufficient balance.
