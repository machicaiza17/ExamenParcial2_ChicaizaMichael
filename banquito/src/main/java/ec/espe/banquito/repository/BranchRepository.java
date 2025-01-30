package ec.espe.banquito.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.espe.banquito.model.Branch;

public interface BranchRepository extends MongoRepository<Branch, String> {
}
