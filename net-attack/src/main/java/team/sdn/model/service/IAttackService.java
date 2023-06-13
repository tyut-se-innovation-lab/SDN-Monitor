package team.sdn.model.service;

import team.sdn.model.domain.entity.InputParam;
import team.sdn.model.domain.entity.OutputResult;

public interface IAttackService {
    public OutputResult identifyAttack(InputParam inputParam);
}
