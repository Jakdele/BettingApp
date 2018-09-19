package pl.coderslab.service;

import pl.coderslab.entity.BetSLip;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BetSlipService {
    List<BetSLip> getAll();

    BetSLip save(BetSLip betSLip);

    List<BetSLip> getAllByUser(User user);

    void saveNewSlip(BetSLip betSLip, HttpSession session);
}
