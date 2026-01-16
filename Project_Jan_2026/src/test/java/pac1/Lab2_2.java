
package pac1;

public class Lab2_2 {

    static class Person {
        private String name;
        private float age;

        public Person() {}

        public Person(String name, float age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public float getAge() { return age; }
        public void setAge(float age) { this.age = age; }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    static class Account {
        private long accNum;
        protected double balance;          
        private Person accHolder;

        public static final double MIN_BALANCE = 500.0; 

        public Account() {}

        public Account(long accNum, Person accHolder, double openingBalance) {
            this.accNum = accNum;
            this.accHolder = accHolder;
            this.balance = Math.max(openingBalance, MIN_BALANCE);
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be positive.");
                return;
            }
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return false;
            }
            if (balance - amount < MIN_BALANCE) {
                System.out.println("Withdrawal denied: minimum balance of INR "
                        + MIN_BALANCE + " must be maintained.");
                return false;
            }
            balance -= amount;
            return true;
        }

        public double getBalance() { return balance; }

        public long getAccNum() { return accNum; }
        public void setAccNum(long accNum) { this.accNum = accNum; }

        public Person getAccHolder() { return accHolder; }
        public void setAccHolder(Person accHolder) { this.accHolder = accHolder; }

        public void setBalance(double balance) {
            this.balance = Math.max(balance, MIN_BALANCE);
        }

        @Override
        public String toString() {
            return "Account{accNum=" + accNum + ", balance=" + balance +
                    ", accHolder=" + (accHolder != null ? accHolder.getName() : "null") + "}";
        }
    }

    static class SavingsAccount extends Account {
        private final double minimumBalance; 

        public SavingsAccount(long accNum, Person accHolder, double openingBalance, double minimumBalance) {
            super(accNum, accHolder, Math.max(openingBalance, minimumBalance));
            this.minimumBalance = minimumBalance;
        }

        public double getMinimumBalance() { return minimumBalance; }

        @Override
        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return false;
            }
            if (balance - amount < minimumBalance) {
                System.out.println("Savings withdrawal denied: minimum balance of INR "
                        + minimumBalance + " must be maintained.");
                return false;
            }
            balance -= amount;
            return true;
        }

        @Override
        public String toString() {
            return "SavingsAccount{accNum=" + getAccNum() + ", balance=" + balance +
                    ", minimumBalance=" + minimumBalance +
                    ", accHolder=" + (getAccHolder() != null ? getAccHolder().getName() : "null") + "}";
        }
    }

    //CurrentAccount inherit account
    static class CurrentAccount extends Account {
        private double overdraftLimit;

        public CurrentAccount(long accNum, Person accHolder, double openingBalance, double overdraftLimit) {
            super(accNum, accHolder, Math.max(openingBalance, 0)); 
            this.overdraftLimit = Math.max(0, overdraftLimit);
            this.balance = openingBalance; 
        }

        public double getOverdraftLimit() { return overdraftLimit; }
        public void setOverdraftLimit(double overdraftLimit) {
            this.overdraftLimit = Math.max(0, overdraftLimit);
        }

        @Override
        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return false;
            }
            double prospective = balance - amount;
            if (prospective < -overdraftLimit) {
                System.out.println("Current withdrawal denied: overdraft limit of INR "
                        + overdraftLimit + " would be exceeded.");
                return false;
            }

            balance = prospective;
            return true;
        }

        @Override
        public String toString() {
            return "CurrentAccount{accNum=" + getAccNum() + ", balance=" + balance +
                    ", overdraftLimit=" + overdraftLimit +
                    ", accHolder=" + (getAccHolder() != null ? getAccHolder().getName() : "null") + "}";
        }
    }

    public static void main(String[] args) {
        Person holder = new Person("Ansh", 21.0f);

        
        Account baseAcc = new Account(1234567890123L, holder, 400); 
        System.out.println("Base Opening balance: " + baseAcc.getBalance());
        baseAcc.deposit(800);
        System.out.println("Base After deposit: " + baseAcc.getBalance());
        baseAcc.withdraw(700);
        System.out.println("Base After withdrawal: " + baseAcc.getBalance());
        baseAcc.withdraw(600);
        System.out.println("Base Final balance: " + baseAcc.getBalance());
        System.out.println();

        SavingsAccount savAcc = new SavingsAccount(9876543210001L, holder, 400, 1000);
        System.out.println("Savings Opening balance: " + savAcc.getBalance());
        savAcc.deposit(800);
        System.out.println("Savings After deposit: " + savAcc.getBalance());
        savAcc.withdraw(700);  
        System.out.println("Savings After withdrawal: " + savAcc.getBalance());
        savAcc.withdraw(600);
        System.out.println("Savings Final balance: " + savAcc.getBalance());
        System.out.println();


        CurrentAccount curAcc = new CurrentAccount(5555555555555L, holder, 500, 2000);
        System.out.println("Current Opening balance: " + curAcc.getBalance());
        curAcc.deposit(500);
        System.out.println("Current After deposit: " + curAcc.getBalance());

        boolean w1 = curAcc.withdraw(1200); 
        System.out.println("Current Withdraw 1200 success? " + w1 + ", balance: " + curAcc.getBalance());

        boolean w2 = curAcc.withdraw(2000); 
        System.out.println("Current Withdraw 2000 success? " + w2 + ", balance: " + curAcc.getBalance());

        System.out.println("Current Final balance: " + curAcc.getBalance());
    }
}
