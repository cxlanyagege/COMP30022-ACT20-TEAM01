/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: aap service do operations related to database
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.project.application.mapper.AAPMapper;
import it.project.application.pojo.AAP;
import it.project.application.service.IAAPService;
import org.springframework.stereotype.Service;

@Service
public class AAPServiceImpl extends ServiceImpl<AAPMapper, AAP> implements IAAPService {
}
