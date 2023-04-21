package NamedJDMCOper.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public interface StudService {
    List<Double> AvgMarkOnCurs();
}