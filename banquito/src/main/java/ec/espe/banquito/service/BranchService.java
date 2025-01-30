package ec.espe.banquito.service;

import ec.espe.banquito.controller.dto.*;
import ec.espe.banquito.model.Branch;
import ec.espe.banquito.model.BranchHolidays;
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

    public BranchResponseDTO createBranch(CreateBranchDTO dto) {
        Branch branch = new Branch();
        branch.setName(dto.getName());
        branch.setEmailAddress(dto.getEmailAddress());
        branch.setPhoneNumber(dto.getPhoneNumber());
        branch.setState(dto.getState());
        branch.setCreationDate(LocalDateTime.now());
        branch.setLastModifiedDate(LocalDateTime.now());

        branchRepository.save(branch);
        return mapToResponseDTO(branch);
    }

    public BranchResponseDTO updateBranchPhone(String id, UpdateBranchPhoneDTO dto) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Sucursal no encontrada");
                });

        branch.setPhoneNumber(dto.getPhoneNumber());
        branch.setLastModifiedDate(LocalDateTime.now());
        branchRepository.save(branch);
        return mapToResponseDTO(branch);
    }

    public void deleteHolidaysFromBranch(String id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Sucursal no encontrada");
                });

        branch.setBranchHolidays(null);
        branch.setLastModifiedDate(LocalDateTime.now());
        branchRepository.save(branch);
    }

    public boolean isDateHoliday(String id, LocalDate date) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Sucursal no encontrada");
                });
        boolean isHoliday = branch.getBranchHolidays().stream()
                .anyMatch(h -> h.getDate().equals(date));

        return isHoliday;
    }

    private BranchResponseDTO mapToResponseDTO(Branch branch) {
        BranchResponseDTO dto = new BranchResponseDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setEmailAddress(branch.getEmailAddress());
        dto.setPhoneNumber(branch.getPhoneNumber());
        dto.setState(branch.getState());
        dto.setCreationDate(branch.getCreationDate());
        dto.setLastModifiedDate(branch.getLastModifiedDate());
        return dto;
    }

}
