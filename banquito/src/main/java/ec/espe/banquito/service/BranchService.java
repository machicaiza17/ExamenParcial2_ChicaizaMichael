package ec.espe.banquito.service;

import ec.espe.banquito.controller.dto.*;
import ec.espe.banquito.model.Branch;
import ec.espe.banquito.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchResponseDTO> getAllBranches() {
        return branchRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public BranchResponseDTO getBranchById(String id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Sucursal no encontrada");
                });
        return mapToResponseDTO(branch);
    }

    
}
