package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.Wallet;
import pl.coderslab.repository.OperationRepository;
import pl.coderslab.service.OperationService;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private OperationRepository operationRepository;
    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Transaction> getAll() {
        return operationRepository.findAll();
    }

    @Override
    public List<Transaction> getAllByWallet(Wallet wallet) {
        return operationRepository.findAllByWallet(wallet);
    }

}
