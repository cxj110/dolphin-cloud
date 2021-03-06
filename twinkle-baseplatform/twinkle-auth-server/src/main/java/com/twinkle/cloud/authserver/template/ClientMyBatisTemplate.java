package com.twinkle.cloud.authserver.template;

import com.twinkle.cloud.authserver.data.domain.Client;
import com.twinkle.cloud.authserver.data.domain.ClientService;
import com.twinkle.cloud.authserver.mapper.ClientMapper;
import com.twinkle.cloud.authserver.mapper.ClientServiceMapper;
import com.twinkle.cloud.common.mybatis.template.AbstractMapperTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * ClassName: ClientMyBatisTemplate <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
public class ClientMyBatisTemplate extends AbstractMapperTemplate<ClientMapper, Client> {
    @Autowired
    private ClientServiceMapper clientServiceMapper;
    @Autowired
    private ClientServiceMyBatisTemplate clientServiceBiz;

    public List<Client> getClientServices(int id) {
        return mapper.selectAuthorityServiceInfo(id);
    }

    public void modifyClientServices(int id, String clients) {
        clientServiceMapper.deleteByServiceId(id);
        if (!StringUtils.isEmpty(clients)) {
            String[] mem = clients.split(",");
            for (String m : mem) {
                ClientService clientService = new ClientService();
                clientService.setServiceId(m);
                clientService.setClientId(id + "");
                clientServiceBiz.insertSelective(clientService);
            }
        }
    }
}