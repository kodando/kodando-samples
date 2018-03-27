# Mithril + Webpack

## Configure

1. Import this directory as a gradle project using Idea for configuring the project on the IDE;

## Dependencies 

1. Install NodeJs 8+;
2. Download the dependencies for node: `yarn` or `npm install`;
3. Idea or gradle will download the dependencies on the run.

## How to build?

1. Build the kotlin files into javascript: `./gradlew build`;
2. Bundle javascript modules: `yarn build` or `npm run build`;

This will generate a directory named `dist` with an `index.html` file and 
the bundled javascript file.

## How to execute?

After executing the build:

1. Start a local webserver: `yarn serve` or `npm run serve`;
2. Navigate to http://localhost:5000.

## How to watch, test and run in dev mode?

There are some ways to to that. In this example, we won't use hot replacement 
from webpack, we will still use the hot reload, and you will need to open 
two terminals to do that.

**Terminal 1**

In this terminal, we will start the gradle interactive build to compile kotlin
to js every time that we change anything.

1. Execute gradle: `./gradlew -t build`.

**Terminal 2**

In this terminal, we will start the `webpack-dev-server` to watch, build and
serve our front-end application every time that the kotlin compiler produces
new javascript files.

1. Exeucte webpack-dev-server: `yarn start` or `npm run start`.
