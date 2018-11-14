FROM r-base:3.5.1

RUN apt-get update
RUN apt-get -y install git wget gpg python maven
COPY ./ttc2018liveContest ./ttc2018liveContest
# RUN git clone https://github.com/TransformationToolContest/ttc2018liveContest

# Install .NET
RUN wget -qO- https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.asc.gpg
RUN mv microsoft.asc.gpg /etc/apt/trusted.gpg.d/
RUN wget -q https://packages.microsoft.com/config/debian/8/prod.list
RUN mv prod.list /etc/apt/sources.list.d/microsoft-prod.list
RUN chown root:root /etc/apt/trusted.gpg.d/microsoft.asc.gpg
RUN chown root:root /etc/apt/sources.list.d/microsoft-prod.list

# Build project
WORKDIR ./ttc2018liveContest
RUN echo pwd
RUN echo ls
RUN python ./scripts/run.py -b 

# run benchmark
CMD python ./scripts/run.py -m -t; echo "TEST FINISHED"; while true; do sleep 300; done