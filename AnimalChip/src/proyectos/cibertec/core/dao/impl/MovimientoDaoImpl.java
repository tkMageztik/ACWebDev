package proyectos.cibertec.core.dao.impl;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.MovimientoDao;
import proyectos.cibertec.core.entity.Movimiento;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("movimientoDao")
public class MovimientoDaoImpl extends EntityDaoImpl<Movimiento> implements MovimientoDao{

}
