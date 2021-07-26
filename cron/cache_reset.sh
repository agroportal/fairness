#!/bin/bash
script="$(basename $0)"

usage() {
  echo
  echo "Usage:"
  echo
  echo "  $script"
  echo "    <fairness war directory path> <environment>"
  echo "    -h|--help"
  echo
  echo "--------------------------------------------------"
  echo
  echo "Examples :"
  echo
  echo "  $script /usr/share/tomacat/webapps/fairness-assessment prod"
  echo "  $script /usr/share/tomacat/webapps/fairness-assessment dev"
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
    echo "two arguments must be supplied : <fairness war directory path> and <environment>"
    usage
    exit 0
fi

war_dir=$1
env=$2

echo "check if the directory $war_dir/WEB-INF exist ?"
if [ -d "$war_dir/WEB-INF" ]
 then
    cd $war_dir'/WEB-INF'
    path=$(pwd)
    java_cmd="java -Denv=$env -classpath \"./lib/\*:./classes/.\" fr.lirmm.fairness.assessment.CacheSaverCMD"
    echo "[+] Running '$java_cmd' in '$path' "
    java -Denv=$env -classpath "lib/*:./classes/." fr.lirmm.fairness.assessment.CacheSaverCMD > ~/log/FAIR_CACHE/$(date +\%Y\%m\%d\%H\%M\%S)_chache_all.log 2>&1

else
  echo "the war directory of FAIR assessment is not found in : $war_dir"
  exit 2
fi
exit 0