#!/bin/bash
script="$(basename $0)"

usage() {
  echo
  echo "Usage:"
  echo
  echo "  $script"
  echo "    <fairness war directory path>"
  echo "    -h|--help"
  echo
  echo "--------------------------------------------------"
  echo
  echo "Examples :"
  echo
  echo "  $script /usr/share/tomacat/webapps/fairness-assessment"
  echo "  $script /usr/share/tomacat/webapps/fairness-assessment"
  echo
}

while getopts "$optspec" optchar; do
  case "${OPTARG}" in
    'help' | 'h')
      usage
      exit 0
      ;;
    *)
      ;;
  esac
done

if [ $# -eq 0 ]
  then
    echo "one argument must be supplied : <fairness war directory path>"
    usage
    exit 0
fi

war_dir=$1
log_dir_path="/var/log/tomcat/log/FAIR_CACHE"
log_file_name="$log_dir_path/$(date +\%Y\%m\%d\%H\%M\%S)_cache_all.log"
echo "check if the directory $war_dir/WEB-INF exist ?"
if [ -d "$war_dir/WEB-INF" ]
 then
    cd $war_dir'/WEB-INF'
    path=$(pwd)
    java_cmd="java -classpath \"./lib/\*:./classes/.\" fr.lirmm.fairness.assessment.CacheSaverCMD"
    echo "[+] Running '$java_cmd' in '$path' "
     if [ ! -d $log_dir_path ]; then
       mkdir -r /var/log/tomcat/log/FAIR_CACHE
     fi
    touch log_file_name
    java -classpath "lib/*:./classes/." fr.lirmm.fairness.assessment.CacheSaverCMD  2>&1 | tee log_file_name

else
  echo "the war directory of FAIR assessment is not found in : $war_dir"
  exit 2
fi
exit 0