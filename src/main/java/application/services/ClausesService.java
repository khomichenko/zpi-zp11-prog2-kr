package application.services;

import application.data.ClauseRepository;
import application.model.Clause;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClausesService {
    private final ClauseRepository repo;

    public List<Clause> list() {
        return repo.findAll();
    }

    public Boolean has(Long id) {
        return repo.findByClauseId(id).isEmpty()==false;
    }

    public Clause get(Long id) {
        for (Clause clause: repo.findByClauseId(id)) {
            return clause;
        }
        return null;
    }

    public List<Clause> get(Long... ids) {
        List<Clause> list = new ArrayList<>();
        for (Long id: ids) {
            for (Clause clause: repo.findByClauseId(id)) {
                list.add(clause);
            }
        }
        return list;
    }

    public Clause save(Clause clause) {
        return repo.saveAndFlush(clause);
    }
}