#!/bin/bash

# Ruta donde está tu repositorio
REPO_DIR="$HOME/meinirrgarten"

# Ruta donde se guardará la copia de seguridad
BACKUP_DIR="$HOME/meinirrgarten-backup"

# Crea la carpeta de backup si no existe
mkdir -p "$BACKUP_DIR"

# Sincroniza los archivos del repositorio al backup
rsync -av --delete "$REPO_DIR/" "$BACKUP_DIR/"

# Mensaje de confirmación
echo "Backup completado en: $BACKUP_DIR"

