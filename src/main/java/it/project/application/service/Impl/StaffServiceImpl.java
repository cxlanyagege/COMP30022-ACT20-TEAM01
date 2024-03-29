/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: staff service do operations related to database
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.project.application.mapper.StaffMapper;
import it.project.application.pojo.Staff;
import it.project.application.service.IStaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {
}
