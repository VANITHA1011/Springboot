/*package com.infosys.budgettracker.service;

import com.infosys.budgettracker.model.TransactionEntity;
import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.repository.TransactionRepository;
import com.infosys.budgettracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public TransactionEntity addTransaction(TransactionEntity transaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        transaction.setUser(user);

        // Optional: validate type
        if (!transaction.getType().equalsIgnoreCase("INCOME") &&
            !transaction.getType().equalsIgnoreCase("EXPENSE")) {
            throw new Exception("Transaction type must be INCOME or EXPENSE");
        }

        return transactionRepository.save(transaction);
    }

    public List<TransactionEntity> getAllTransactions(String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        return transactionRepository.findByUser(user);
    }
}*/
/*package com.infosys.budgettracker.service;

import com.infosys.budgettracker.model.TransactionEntity;
import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.repository.TransactionRepository;
import com.infosys.budgettracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Add Transaction
    public TransactionEntity addTransaction(TransactionEntity transaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        transaction.setUser(user);

        // Validate type
        if (!transaction.getType().equalsIgnoreCase("INCOME") &&
            !transaction.getType().equalsIgnoreCase("EXPENSE")) {
            transaction.setType("EXPENSE");
        }

        return transactionRepository.save(transaction);
    }

    // Get all transactions of logged-in user
    public List<TransactionEntity> getAllTransactions(String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        return transactionRepository.findByUser(user);
    }

    // Update transaction
    public TransactionEntity updateTransaction(Long id, TransactionEntity updatedTransaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot edit this transaction");
        }

        // Update fields
        transaction.setType(updatedTransaction.getType());
        transaction.setCategory(updatedTransaction.getCategory());
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDescription(updatedTransaction.getDescription());
        transaction.setAccount(updatedTransaction.getAccount());
        transaction.setDate(updatedTransaction.getDate());

        return transactionRepository.save(transaction);
    }

    // Delete transaction
    public void deleteTransaction(Long id, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot delete this transaction");
        }

        transactionRepository.delete(transaction);
    }
}*/
/*package com.infosys.budgettracker.service;

import com.infosys.budgettracker.dto.TransactionDTO;
import com.infosys.budgettracker.model.TransactionEntity;
import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.repository.TransactionRepository;
import com.infosys.budgettracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Add Transaction
    public TransactionEntity addTransaction(TransactionEntity transaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        transaction.setUser(user);

        // Validate type
        if (!transaction.getType().equalsIgnoreCase("INCOME") &&
            !transaction.getType().equalsIgnoreCase("EXPENSE")) {
            transaction.setType("EXPENSE");
        }

        return transactionRepository.save(transaction);
    }

    // Get all transactions of logged-in user as DTO
    public List<TransactionDTO> getAllTransactions(String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        return transactionRepository.findByUser(user).stream()
                .map(t -> new TransactionDTO(
                        t.getId(),
                        t.getType(),
                        t.getCategory(),
                        t.getAmount(),
                        t.getDescription(),
                        t.getAccount(),
                        t.getDate(),
                        t.getUser().getUsername() // only username
                ))
                .toList();
    }

    // Update transaction
    public TransactionEntity updateTransaction(Long id, TransactionEntity updatedTransaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot edit this transaction");
        }

        // Update fields
        transaction.setType(updatedTransaction.getType());
        transaction.setCategory(updatedTransaction.getCategory());
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDescription(updatedTransaction.getDescription());
        transaction.setAccount(updatedTransaction.getAccount());
        transaction.setDate(updatedTransaction.getDate());

        return transactionRepository.save(transaction);
    }

    // Delete transaction
    public void deleteTransaction(Long id, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot delete this transaction");
        }

        transactionRepository.delete(transaction);
    }
}*/
/*package com.infosys.budgettracker.service;

import com.infosys.budgettracker.dto.TransactionDTO;
import com.infosys.budgettracker.model.TransactionEntity;
import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.repository.TransactionRepository;
import com.infosys.budgettracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Add Transaction (returns DTO without user)
    public TransactionDTO addTransaction(TransactionEntity transaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        transaction.setUser(user);

        if (!transaction.getType().equalsIgnoreCase("INCOME") &&
            !transaction.getType().equalsIgnoreCase("EXPENSE")) {
            transaction.setType("EXPENSE");
        }

        TransactionEntity saved = transactionRepository.save(transaction);

        // Return DTO only
        return new TransactionDTO(
                saved.getId(),
                saved.getType(),
                saved.getCategory(),
                saved.getAmount(),
                saved.getDescription(),
                saved.getAccount(),
                saved.getDate(),
                saved.getUser().getUsername()
        );
    }

    // ✅ Get all transactions as DTO
    public List<TransactionDTO> getAllTransactions(String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        return transactionRepository.findByUser(user).stream()
                .map(t -> new TransactionDTO(
                        t.getId(),
                        t.getType(),
                        t.getCategory(),
                        t.getAmount(),
                        t.getDescription(),
                        t.getAccount(),
                        t.getDate(),
                        t.getUser().getUsername()
                ))
                .toList();
    }

    // Update Transaction
    public TransactionEntity updateTransaction(Long id, TransactionEntity updatedTransaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot edit this transaction");
        }

        transaction.setType(updatedTransaction.getType());
        transaction.setCategory(updatedTransaction.getCategory());
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDescription(updatedTransaction.getDescription());
        transaction.setAccount(updatedTransaction.getAccount());
        transaction.setDate(updatedTransaction.getDate());

        return transactionRepository.save(transaction);
    }

    // Delete Transaction
    public void deleteTransaction(Long id, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot delete this transaction");
        }

        transactionRepository.delete(transaction);
    }
}*/
package com.infosys.budgettracker.service;

import com.infosys.budgettracker.dto.TransactionDTO;
import com.infosys.budgettracker.model.TransactionEntity;
import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.repository.TransactionRepository;
import com.infosys.budgettracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Add Transaction (returns DTO)
    public TransactionDTO addTransaction(TransactionEntity transaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        transaction.setUser(user);

        if (!transaction.getType().equalsIgnoreCase("INCOME") &&
            !transaction.getType().equalsIgnoreCase("EXPENSE")) {
            transaction.setType("EXPENSE");
        }

        TransactionEntity saved = transactionRepository.save(transaction);

        return new TransactionDTO(
                saved.getId(),
                saved.getType(),
                saved.getCategory(),
                saved.getAmount(),
                saved.getDescription(),
                saved.getAccount(),
                saved.getDate(),
                saved.getUser().getUsername()
        );
    }

    // Get all transactions as DTO
    public List<TransactionDTO> getAllTransactions(String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        return transactionRepository.findByUser(user).stream()
                .map(t -> new TransactionDTO(
                        t.getId(),
                        t.getType(),
                        t.getCategory(),
                        t.getAmount(),
                        t.getDescription(),
                        t.getAccount(),
                        t.getDate(),
                        t.getUser().getUsername()
                ))
                .toList();
    }

    // Update transaction (returns DTO)
    public TransactionDTO updateTransaction(Long id, TransactionEntity updatedTransaction, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot edit this transaction");
        }

        transaction.setType(updatedTransaction.getType());
        transaction.setCategory(updatedTransaction.getCategory());
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDescription(updatedTransaction.getDescription());
        transaction.setAccount(updatedTransaction.getAccount());
        transaction.setDate(updatedTransaction.getDate());

        TransactionEntity saved = transactionRepository.save(transaction);

        return new TransactionDTO(
                saved.getId(),
                saved.getType(),
                saved.getCategory(),
                saved.getAmount(),
                saved.getDescription(),
                saved.getAccount(),
                saved.getDate(),
                saved.getUser().getUsername()
        );
    }

    // Delete Transaction
    public void deleteTransaction(Long id, String authHeader) throws Exception {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot delete this transaction");
        }

        transactionRepository.delete(transaction);
    }
}




