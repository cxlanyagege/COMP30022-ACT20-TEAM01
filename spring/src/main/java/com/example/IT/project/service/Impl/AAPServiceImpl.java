package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.AAPMapper;
import com.example.it.project.pojo.AAP;
import com.example.it.project.service.IAAPService;
import org.springframework.stereotype.Service;

@Service
public class AAPServiceImpl extends ServiceImpl<AAPMapper, AAP> implements IAAPService {
}
