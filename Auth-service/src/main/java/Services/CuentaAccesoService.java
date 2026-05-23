package Services;

import Exceptions.CorreoDuplicadoException;
import Exceptions.CuentaNoEncontradaException;
import Models.CuentaAcceso;
import Repositories.CuentaAccesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaAccesoService {

    // Gestiona las cuentas evitando correos duplicados y las desactiva para mantener el historial.

    @Autowired
    private CuentaAccesoRepository repository;

    public CuentaAcceso crearCuenta(CuentaAcceso cuenta) {
        if (repository.findByEmail(cuenta.getEmail()).isPresent()) {
            throw new CorreoDuplicadoException("El correo " + cuenta.getEmail() + " ya está registrado.");
        }
        cuenta.setPassword(cuenta.getPassword());
        cuenta.setEstado("ACTIVO");
        return repository.save(cuenta);
    }

    public CuentaAcceso desactivarCuenta(Long id) {
        CuentaAcceso cuenta = repository.findById(id)
                .orElseThrow(() -> new CuentaNoEncontradaException("No existe la cuenta con ID: " + id));
        cuenta.setEstado("INACTIVO");
        return repository.save(cuenta);
    }

    }
