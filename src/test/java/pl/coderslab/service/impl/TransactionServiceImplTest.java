package pl.coderslab.service.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.Wallet;
import pl.coderslab.entity.enums.TransactionType;
import pl.coderslab.repository.TransactionRepository;
import pl.coderslab.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionServiceImplTest {

    private TransactionService service;
    private TransactionRepository repository;


    @Before
    public void setUp() {
        repository = mock(TransactionRepository.class);
        service = new TransactionServiceImpl(repository);

    }

    @Test
    public void findAllbyWalletId() {
        // given

        Wallet wallet = new Wallet();
        wallet.setId(1);
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        Transaction transaction1 = new Transaction();
        transaction.setWallet(wallet);
        transaction1.setWallet(wallet);

        transactions.add(transaction);
        transactions.add(transaction1);

        when(repository.findByWalletId(wallet.getId())).thenReturn(transactions);

        // when
        List<Transaction> result = service.findAllbyWalletId(wallet.getId());

        // then
        assertEquals(2,result.size());
        assertThat(result).containsExactly(transaction,transaction1);
        assertEquals(transactions, result);
        MatcherAssert.assertThat(transactions, is(result));
    }

    @Test
    public void save() {
        // given
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        when(repository.save(transaction)).thenReturn(transaction);

        // when
        Transaction result = service.save(transaction);

        // then
        assertNotNull(result);
        assertEquals(transaction.getTransactionType(), result.getTransactionType());

    }

    @Test
    public void findAll() {

        //given
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        transactions.add(transaction);
        transactions.add(transaction1);
        transactions.add(transaction2);
        when(repository.findAll()).thenReturn(transactions);

        //when
        List<Transaction> result = service.findAll();

        //then
        assertEquals(3,result.size());
        assertThat(result).containsExactly(transaction,transaction1, transaction2);
        assertEquals(transactions, result);
        MatcherAssert.assertThat(transactions, is(result));
    }
}