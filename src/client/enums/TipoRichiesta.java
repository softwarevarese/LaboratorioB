package client.enums;

/**
 * Descrive tutte le possibili richieste che possono essere inoltrate dal client al server
 *
 */
public enum TipoRichiesta {
	Login, Register, Close, GetDipartimenti, GetCorsiLaurea, GetCorsiMateria, CreateUser, GetFileSystem,
	DownloadDocumento, CreaSezione, UploadDocumento, PasswordDimenticata, ConfermaCodiceUtente, GetCorsiMateriaByDip,
	CreateCorsoMateria, GetAllDocenti, UpdateLoc, getMediaSecCorso, getLoggedUserNumber, getLoggedUserNumberByCorso,
	getNumberAccessByPeriod, UpdateDocumentCount, DocumentContByCorso, ContUtentiDownload, GetAllCorsiMateria, SubscribeCourse,
	VerificaCod, GetCorsiStud, GetCorsiDoc, BloccaAccount, ModificaCorsoMateria, SendNewsLetter, LinkDocenteCorso, CambiaPassword, ModificaAnagrafica,
	GetAllStudenti, GetStudenteByMat, GetCorsoLaureaByID, CambiaVisSez, isVerificato
}
