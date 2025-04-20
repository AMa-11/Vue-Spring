package task.crypto.User;

import jakarta.persistence.Entity;

@Entity
class User {

    // Assume constant currency for this simple case.
    private float mBalance; 
    // Could just add a private string mCurrency

    User() {}

    User(float balance) {
        this.mBalance = balance;
    }

    public float getBalance() {
        return this.mBalance;
    }

    public void setBalance(float newBalance) {
        this.mBalance = newBalance;        
    }
}
