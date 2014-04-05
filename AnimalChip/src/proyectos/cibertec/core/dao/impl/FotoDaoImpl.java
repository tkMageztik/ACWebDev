package proyectos.cibertec.core.dao.impl;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.FotoDao;
import proyectos.cibertec.core.entity.Foto;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("fotoDao")
public class FotoDaoImpl extends EntityDaoImpl<Foto> implements FotoDao{

}
