/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: staff service do operations related to database
 */

package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.StaffMapper;
import com.example.it.project.pojo.Staff;
import com.example.it.project.service.IStaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {
}
