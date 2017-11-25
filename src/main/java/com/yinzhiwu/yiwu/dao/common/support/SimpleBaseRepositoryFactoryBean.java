package com.yinzhiwu.yiwu.dao.common.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.common.BaseRepository;
import com.yinzhiwu.yiwu.dao.common.callback.SearchCallback;
import com.yinzhiwu.yiwu.dao.common.support.annotation.SearchableQuery;

/**
 * 基础Repostory简单实现 factory bean
 * 请参考 spring-data-jpa-reference [1.4.2. Adding custom behaviour to all repositories]
 * @author ping
 * @date 2017年11月25日下午3:47:59
 * @since v2.1.5
 *	
 * @param <R> 
 * @param <M> 
 * @param <ID>
 */
public class SimpleBaseRepositoryFactoryBean<R extends JpaRepository<M, ID>, M, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, M, ID> {


    public SimpleBaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new SimpleBaseRepositoryFactory(entityManager);
    }
}

class SimpleBaseRepositoryFactory<M, ID extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public SimpleBaseRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    protected Object getTargetRepository(RepositoryMetadata metadata) {
        Class<?> repositoryInterface = metadata.getRepositoryInterface();

        if (isBaseRepository(repositoryInterface)) {

            JpaEntityInformation<M, ID> entityInformation = getEntityInformation((Class<M>) metadata.getDomainType());
            SimpleBaseRepository repository = new SimpleBaseRepository<M, ID>(entityInformation, entityManager);

            SearchableQuery searchableQuery = AnnotationUtils.findAnnotation(repositoryInterface, SearchableQuery.class);
            if (searchableQuery != null) {
                String countAllQL = searchableQuery.countAllQuery();
                if (!StringUtils.isEmpty(countAllQL)) {
                    repository.setCountAllQL(countAllQL);
                }
                String findAllQL = searchableQuery.findAllQuery();
                if (!StringUtils.isEmpty(findAllQL)) {
                    repository.setFindAllQL(findAllQL);
                }
                Class<? extends SearchCallback> callbackClass = searchableQuery.callbackClass();
                if (callbackClass != null && callbackClass != SearchCallback.class) {
//                    repository.setSearchCallback(BeanUtils.instantiate(callbackClass));
                }

//                repository.setJoins(searchableQuery.joins());

            }

            return repository;
        }
//        return super.getTargetRepository(metadata);
		return repositoryInterface;
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isBaseRepository(metadata.getRepositoryInterface())) {
            return SimpleBaseRepository.class;
        }
        return super.getRepositoryBaseClass(metadata);
    }

    private boolean isBaseRepository(Class<?> repositoryInterface) {
        return BaseRepository.class.isAssignableFrom(repositoryInterface);
    }

}
